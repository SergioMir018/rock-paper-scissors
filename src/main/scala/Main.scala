package com.demo.rps

import scala.annotation.tailrec

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
  val canContinue: Option[(Boolean, Boolean, Boolean | Unit)] = userInput(input)

  canContinue match {
    case Some((continue, isException, isWinner: Boolean))
      if continue && isWinner && !isException =>
      printInScreen(countUser + 1, countPC)
    case Some((continue, isException, isWinner: Boolean))
      if continue && !isWinner && !isException =>
      printInScreen(countUser, countPC + 1)
    case Some(_) => printInScreen(countUser, countPC)
    case None =>
  }
}

def userInput(user: String): Option[(Boolean, Boolean, Boolean | Unit)] = {
  try {
    val input: Int = user.toInt

    val pcPlayer = new Game

    input match {
      case 0 =>
        println(s"${Console.CYAN}Exited from game ${Console.RESET}")
        None
      case 1 | 2 | 3 =>
        val result  = pcPlayer.giveResult(input) match {
          case Some(result) => result
          case None =>
        }
        Some((true, false, result))
      case _ =>
        println(s"${Console.YELLOW}Please enter a valid option${Console.RESET}")
        Some((false, false, false))
    }
  } catch
    case e: NumberFormatException =>
      println(s"${Console.RED}Enter a valid number${Console.RESET}")
      Some(true, true, false)
}

