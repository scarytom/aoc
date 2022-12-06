(ns com.scarytom.aoc.xmas2021.day02
  (:require [com.scarytom.aoc.utils :as utils]))

(def re-instruction #"(forward|down|up) (\d+)")
(defn read-instruction [line]
  (let [[_ direction value] (re-matches re-instruction line)]
    [(keyword direction) (utils/to-int value)]))

(defn part-1-reducer [[horz depth] [direction value]]
  (case direction
    :up [horz (- depth value)]
    :down [horz (+ depth value)]
    :forward [(+ horz value) depth]))

(defn part-2-reducer [[horz depth aim] [direction value]]
  (case direction
    :up [horz depth (- aim value)]
    :down [horz depth (+ aim value)]
    :forward [(+ horz value) (+ depth (* value aim)) aim]))

(defn apply-instructions [reducer lines]
  (let [[horz depth] (reduce reducer (repeat 0) (map read-instruction lines))]
    (* horz depth)))

(defn part-1 []
  (utils/read-input-file 2021 2 (partial apply-instructions part-1-reducer)))

(defn part-2 []
  (utils/read-input-file 2021 2 (partial apply-instructions part-2-reducer)))
