(ns com.scarytom.aoc.xmas2021.day01-test
  (:require [clojure.test :refer [deftest is]]
            [com.scarytom.aoc.xmas2021.day01 :as day01]))

(deftest part-1-solution
  (is (= (day01/part-1) 1288)))

(deftest part-2-solution
  (is (= (day01/part-2) 1311)))