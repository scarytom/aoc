(ns com.scarytom.aoc.xmas2022.day01-test
  (:require [clojure.test :refer [deftest is]]
            [com.scarytom.aoc.xmas2022.day01 :as day01]))

(def sample-lines ["1000"
                   "2000"
                   "3000"
                   ""
                   "4000"
                   ""
                   "5000"
                   "6000"
                   ""
                   "7000"
                   "8000"
                   "9000"
                   ""
                   "10000"])

(deftest calculates-elf-snack-calories
  (is (= (day01/read-elf-calories sample-lines)
         [6000 4000 11000 24000 10000])))