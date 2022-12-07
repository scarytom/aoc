(ns com.scarytom.aoc.xmas2021.day03-test
  (:require [clojure.test :refer [deftest is]]
            [com.scarytom.aoc.xmas2021.day03 :as day03]))


(deftest part-1-solution
  (is (= (day03/part-1) 4174964)))

(deftest part-2-solution
  (is (= (day03/part-2) 4474944)))