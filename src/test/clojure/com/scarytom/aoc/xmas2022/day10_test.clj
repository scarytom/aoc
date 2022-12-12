(ns com.scarytom.aoc.xmas2022.day10-test
  (:require
    [clojure.java.io :as io]
    [clojure.test :refer [deftest is]]
    [com.scarytom.aoc.xmas2022.day10 :as day10]))

(def simple-sample-lines ["noop"
                          "addx 3"
                          "addx -5"])

(deftest process-simple-sample
  (is (= (day10/process-instructions simple-sample-lines)
         [1 1 1 4 4 -1])))

(deftest process-meaty-sample
  (with-open [reader (io/reader (io/resource "samples/2022/day10.txt"))]
    (let [register-values (day10/process-instructions (line-seq reader))]
      (is (= (nth register-values (dec 20)) 21))
      (is (= (nth register-values (dec 60)) 19))
      (is (= (nth register-values (dec 100)) 18))
      (is (= (nth register-values (dec 140)) 21))
      (is (= (nth register-values (dec 180)) 16))
      (is (= (nth register-values (dec 220)) 18))
      (is (= (map #(nth register-values (dec %)) (range 20 221 40))
             [21 19 18 21 16 18])))))

(deftest calc-signal-strength-of-meaty-sample
  (with-open [reader (io/reader (io/resource "samples/2022/day10.txt"))]
    (is (= (day10/calculate-signal-strength (line-seq reader)) 13140))))

(deftest part-1-solution
  (is (= (day10/part-1) 14560)))

(deftest part-2-solution
  (is (= (day10/part-2)
         ["XXXX.X..X.XXX..X..X.XXXX.XXX..X..X.XXXX."
          "X....X.X..X..X.X..X.X....X..X.X..X....X."
          "XXX..XX...X..X.XXXX.XXX..X..X.X..X...X.."
          "X....X.X..XXX..X..X.X....XXX..X..X..X..."
          "X....X.X..X.X..X..X.X....X....X..X.X...."
          "XXXX.X..X.X..X.X..X.XXXX.X.....XX..XXXX."])))