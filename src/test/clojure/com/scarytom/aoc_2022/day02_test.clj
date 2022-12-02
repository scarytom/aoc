(ns com.scarytom.aoc-2022.day02-test
  (:require [clojure.test :refer [deftest is]]
            [com.scarytom.aoc-2022.day02 :as day02]))

(def sample-lines ["A Y"
                   "B X"
                   "C Z"])

(deftest scores-sample-data-part-1
  (is (= (map day02/line->score-1 sample-lines)
         [8 1 6])))

(deftest scores-sample-data-part-2
  (is (= (map day02/line->score-2 sample-lines)
         [4 1 7])))