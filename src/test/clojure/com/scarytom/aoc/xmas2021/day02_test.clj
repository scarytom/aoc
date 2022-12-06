(ns com.scarytom.aoc.xmas2021.day02-test
  (:require [clojure.test :refer [deftest is]]
            [com.scarytom.aoc.xmas2021.day02 :as day02]))

(def sample-lines ["forward 5"
                   "down 5"
                   "forward 8"
                   "up 3"
                   "down 8"
                   "forward 2"])

(deftest follows-part1-rules
  (is (= (reduce day02/part-1-reducer [0 0] (map day02/read-instruction sample-lines))
         [15 10])))

(deftest follows-part2-rules
  (is (= (reduce day02/part-2-reducer [0 0 0] (map day02/read-instruction sample-lines))
         [15 60 10])))

(deftest part-1-solution
  (is (= (day02/part-1) 2036120)))

(deftest part-2-solution
  (is (= (day02/part-2) 2015547716)))