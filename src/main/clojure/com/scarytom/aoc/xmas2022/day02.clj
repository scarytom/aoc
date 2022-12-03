(ns com.scarytom.aoc.xmas2022.day02
  (:require [clojure.java.io :as io]))

(def line->score-1 {
                  "A X" (+ 1 3) ; rock/rock
                  "A Y" (+ 2 6) ; rock/paper
                  "A Z" (+ 3 0) ; rock/scissors
                  "B X" (+ 1 0) ; paper/rock
                  "B Y" (+ 2 3) ; paper/paper
                  "B Z" (+ 3 6) ; paper/scissors
                  "C X" (+ 1 6) ; scissors/rock
                  "C Y" (+ 2 0) ; scissors/paper
                  "C Z" (+ 3 3) ; scissors/scissors
                  })

(def line->score-2 {
                    "A X" (+ 3 0) ; rock/scissors
                    "A Y" (+ 1 3) ; rock/rock
                    "A Z" (+ 2 6) ; rock/paper
                    "B X" (+ 1 0) ; paper/rock
                    "B Y" (+ 2 3) ; paper/paper
                    "B Z" (+ 3 6) ; paper/scissors
                    "C X" (+ 2 0) ; scissors/paper
                    "C Y" (+ 3 3) ; scissors/scissors
                    "C Z" (+ 1 6) ; scissors/rock
                    })

(defn score-strategy [line->score]
  (with-open [reader (io/reader (io/resource "inputs/2022/day02.txt"))]
    (->> (line-seq reader)
         (map line->score)
         (reduce +))))

(defn part-1 []
  (score-strategy line->score-1))

(defn part-2 []
  (score-strategy line->score-2))
