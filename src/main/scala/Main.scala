package com.demo.rps

import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

object Main extends App {
  printInScreen()
}

@tailrec
private def printInScreen(countUser: Int = 0, countPC: Int = 0): Unit = {
  println(
    s"""
      |--------------------------------
      || ${Console.CYAN}Rock${Console.YELLOW} Paper${Console.GREEN} Scissors${Console.RESET} 1..2..3! |
      |--------------------------------
      |""".stripMargin)

  println(
    s"""
      |Current score:
      |Player: $countUser vs PC: $countPC
      |""".stripMargin)

  println(
    """
      |1 - Rock
      |2 - Paper
      |3 - Scissors
      |0 - Exit
      |""".stripMargin
  )

  val input: String = scala.io.StdIn.readLine
  val canContinue: Option[ListBuffer[Boolean]] = userInput(input)

  canContinue match {
    case Some(ListBuffer(continue, isException, isWinner))
      if continue && isWinner && !isException =>
      printInScreen(countUser + 1, countPC)
    case Some(ListBuffer(continue, isException, isWinner))
      if continue && !isWinner && !isException =>
      printInScreen(countUser, countPC + 1)
    case Some(_) => printInScreen(countUser, countPC)
    case None =>
  }
}

private def userInput(user: String): Option[ListBuffer[Boolean]] = {
  try {
    val input: Int = user.toInt

    val pcPlayer = new Game

    input match {
      case 0 =>
        println(s"${Console.CYAN}Exited from game ${Console.RESET}")
        None
      case 1 | 2 | 3 =>
        val result: Option[Boolean] = pcPlayer.giveResult(input)
        val analysisResults: ListBuffer[Boolean] = ListBuffer(true, false)

        result match {
          case Some(true) =>
            println(s"${Console.GREEN}You win!${Console.RESET}")
            analysisResults.addOne(true)
          case Some(false) =>
            println(s"${Console.MAGENTA}You lose!${Console.RESET}")
            analysisResults.addOne(false)
          case None =>
            println(s"${Console.YELLOW}It's a draw!${Console.RESET}")
        }

        Some(analysisResults)
      case _ =>
        println(s"${Console.YELLOW}Please enter a valid option${Console.RESET}")
        Some(ListBuffer(false, false, false))
    }
  } catch
    case e: NumberFormatException =>
      println(s"${Console.RED}Enter a valid number${Console.RESET}")
      Some(ListBuffer(true, true, false))
}

