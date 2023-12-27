package com.demo.rps

class Game:
  private def selectPlay: Option[Gestures] = {
    val random: Int = scala.util.Random.between(1, 4)

    random match
      case 1 => Some(Gestures.SCISSORS)
      case 2 => Some(Gestures.ROCK)
      case 3 => Some(Gestures.PAPER)
      case _ => None
  }

  def giveResult(userPlay: Int): Option[Boolean] = {
    val pcPlay: Gestures | Unit = selectPlay match {
      case Some(play) => play
      case None =>
        println(s"${Console.RED}Unexpected error!${Console.RESET}")
    }

    val result: String = pcPlay match {
      case play if play == Gestures.SCISSORS => resultScissors(userPlay)
      case play if play == Gestures.ROCK => resultRock(userPlay)
      case play if play == Gestures.PAPER => resultPaper(userPlay)
      case _ => ""
    }

    result match {
      case s if s.eq("win") => Some(true)
      case s if s.eq("loose") => Some(false)
      case s if s.eq("draw") => None
      case _ => None
    }
  }

  private def resultScissors(userPlay: Int): String = {
    userPlay match {
      case 1 => "win"
      case 2 => "loose"
      case 3 => "draw"
    }
  }

  private def resultRock(userPlay: Int): String = {
    userPlay match {
      case 1 => "draw"
      case 2 => "win"
      case 3 => "loose"
    }
  }

  private def resultPaper(userPlay: Int): String = {
    userPlay match {
      case 1 => "loos"
      case 2 => "draw"
      case 3 => "win"
    }
  }
end Game
