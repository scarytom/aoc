(ns com.scarytom.aoc.xmas2022.day04-test
  (:require [clojure.test :refer [deftest is]]
            [com.scarytom.aoc.xmas2022.day04 :as day04]))

(def sample-lines ["2-4,6-8"
                   "2-3,4-5"
                   "5-7,7-9"
                   "2-8,3-7"
                   "6-6,4-6"
                   "2-6,4-8"])

(deftest read-assignment-test
  (is (= (day04/read-assignment (first sample-lines))
         [2 4 6 8])))


(deftest part-1-solution
  (is (= (day04/part-1) 500)))

(deftest part-2-solution
  (is (= (day04/part-2) 815)))