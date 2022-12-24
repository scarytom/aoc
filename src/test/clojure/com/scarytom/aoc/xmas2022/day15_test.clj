(ns com.scarytom.aoc.xmas2022.day15-test
  (:require
    [clojure.test :refer [deftest is]]
    [com.scarytom.aoc.xmas2022.day15 :as day15]))

(def sample-lines ["Sensor at x=2, y=18: closest beacon is at x=-2, y=15"
                   "Sensor at x=9, y=16: closest beacon is at x=10, y=16"
                   "Sensor at x=13, y=2: closest beacon is at x=15, y=3"
                   "Sensor at x=12, y=14: closest beacon is at x=10, y=16"
                   "Sensor at x=10, y=20: closest beacon is at x=10, y=16"
                   "Sensor at x=14, y=17: closest beacon is at x=10, y=16"
                   "Sensor at x=8, y=7: closest beacon is at x=2, y=10"
                   "Sensor at x=2, y=0: closest beacon is at x=2, y=10"
                   "Sensor at x=0, y=11: closest beacon is at x=2, y=10"
                   "Sensor at x=20, y=14: closest beacon is at x=25, y=17"
                   "Sensor at x=17, y=20: closest beacon is at x=21, y=22"
                   "Sensor at x=16, y=7: closest beacon is at x=15, y=3"
                   "Sensor at x=14, y=3: closest beacon is at x=15, y=3"
                   "Sensor at x=20, y=1: closest beacon is at x=15, y=3"])

(deftest manhattan-distance-test
  (is (= (day15/manhattan-distance [8 7] [2 10])
         9)))

(deftest count-empties
  (is (= (day15/count-known-empty-coords 10 sample-lines)
         26)))

(deftest find-missing-beacon-tuning-freq-test
  (is (= (day15/find-missing-beacon-tuning-freq 20 sample-lines)
         56000011)))

(deftest part-1-solution
  (is (= (day15/part-1) 4961647)))

(deftest part-2-solution
  (is (= (day15/part-2) 12274327017867)))
