(ns com.scarytom.aoc.xmas2022.day06-test
  (:require [clojure.test :refer [deftest is]]
            [com.scarytom.aoc.xmas2022.day06 :as day06]))

(deftest find-marker-index
  (is (= (day06/find-marker-index 4 "mjqjpqmgbljsphdztnvjfqwrcgsmlb") 7))
  (is (= (day06/find-marker-index 4 "bvwbjplbgvbhsrlpgdmjqwftvncz") 5))
  (is (= (day06/find-marker-index 4 "nppdvjthqldpwncqszvftbrmjlhg") 6))
  (is (= (day06/find-marker-index 4 "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg") 10))
  (is (= (day06/find-marker-index 4 "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw") 11))

  (is (= (day06/find-marker-index 14 "mjqjpqmgbljsphdztnvjfqwrcgsmlb") 19))
  (is (= (day06/find-marker-index 14 "bvwbjplbgvbhsrlpgdmjqwftvncz") 23))
  (is (= (day06/find-marker-index 14 "nppdvjthqldpwncqszvftbrmjlhg") 23))
  (is (= (day06/find-marker-index 14 "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg") 29))
  (is (= (day06/find-marker-index 14 "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw") 26)))

(deftest part-1-solution
  (is (= (day06/part-1) 1929)))

(deftest part-2-solution
  (is (= (day06/part-2) 3298)))