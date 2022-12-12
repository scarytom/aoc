(ns com.scarytom.aoc.xmas2022.day09
  (:require
    [com.scarytom.aoc.utils :as utils]))

(defn read-instruction [line]
  (let [[_ direction magnitude] (re-matches #"(.) (\d+)" line)]
    (repeat (utils/to-int magnitude) (case direction
                                       "L" [-1  0]
                                       "R" [ 1  0]
                                       "U" [ 0  1]
                                       "D" [ 0 -1]))))

(def lookup-table
  ; relative-head-pos, head-movement -> new-relative-head-pos, tail-movement
  {[[-1  1] [-1  0]] [[-1  0] [-1  1]]
   [[-1  1] [ 1  0]] [[ 0  1] [ 0  0]]
   [[-1  1] [ 0  1]] [[ 0  1] [-1  1]]
   [[-1  1] [ 0 -1]] [[-1  0] [ 0  0]]
   [[-1  1] [-1  1]] [[-1  1] [-1  1]]
   [[-1  1] [ 1  1]] [[ 0  1] [ 0  1]]
   [[-1  1] [ 1 -1]] [[ 0  0] [ 0  0]]
   [[-1  1] [-1 -1]] [[-1  0] [-1  0]]

   [[ 0  1] [-1  0]] [[-1  1] [ 0  0]]
   [[ 0  1] [ 1  0]] [[ 1  1] [ 0  0]]
   [[ 0  1] [ 0  1]] [[ 0  1] [ 0  1]]
   [[ 0  1] [ 0 -1]] [[ 0  0] [ 0  0]]
   [[ 0  1] [-1  1]] [[ 0  1] [-1  1]]
   [[ 0  1] [ 1  1]] [[ 0  1] [ 1  1]]
   [[ 0  1] [ 1 -1]] [[ 1  0] [ 0  0]]
   [[ 0  1] [-1 -1]] [[-1  0] [ 0  0]]

   [[ 1  1] [-1  0]] [[ 0  1] [ 0  0]]
   [[ 1  1] [ 1  0]] [[ 1  0] [ 1  1]]
   [[ 1  1] [ 0  1]] [[ 0  1] [ 1  1]]
   [[ 1  1] [ 0 -1]] [[ 1  0] [ 0  0]]
   [[ 1  1] [-1  1]] [[ 0  1] [ 0  1]]
   [[ 1  1] [ 1  1]] [[ 1  1] [ 1  1]]
   [[ 1  1] [ 1 -1]] [[ 1  0] [ 1  0]]
   [[ 1  1] [-1 -1]] [[ 0  0] [ 0  0]]

   [[ 1  0] [-1  0]] [[ 0  0] [ 0  0]]
   [[ 1  0] [ 1  0]] [[ 1  0] [ 1  0]]
   [[ 1  0] [ 0  1]] [[ 1  1] [ 0  0]]
   [[ 1  0] [ 0 -1]] [[ 1 -1] [ 0  0]]
   [[ 1  0] [-1  1]] [[ 0  1] [ 0  0]]
   [[ 1  0] [ 1  1]] [[ 1  0] [ 1  1]]
   [[ 1  0] [ 1 -1]] [[ 1  0] [ 1 -1]]
   [[ 1  0] [-1 -1]] [[ 0 -1] [ 0  0]]

   [[ 1 -1] [-1  0]] [[ 0 -1] [ 0  0]]
   [[ 1 -1] [ 1  0]] [[ 1  0] [ 1 -1]]
   [[ 1 -1] [ 0  1]] [[ 1  0] [ 0  0]]
   [[ 1 -1] [ 0 -1]] [[ 0 -1] [ 1 -1]]
   [[ 1 -1] [-1  1]] [[ 0  0] [ 0  0]]
   [[ 1 -1] [ 1  1]] [[ 1  0] [ 1  0]]
   [[ 1 -1] [ 1 -1]] [[ 1 -1] [ 1 -1]]
   [[ 1 -1] [-1 -1]] [[ 0 -1] [ 0 -1]]

   [[ 0 -1] [-1  0]] [[-1 -1] [ 0  0]]
   [[ 0 -1] [ 1  0]] [[ 1 -1] [ 0  0]]
   [[ 0 -1] [ 0  1]] [[ 0  0] [ 0  0]]
   [[ 0 -1] [ 0 -1]] [[ 0 -1] [ 0 -1]]
   [[ 0 -1] [-1  1]] [[-1  0] [ 0  0]]
   [[ 0 -1] [ 1  1]] [[ 1  0] [ 0  0]]
   [[ 0 -1] [ 1 -1]] [[ 0 -1] [ 1 -1]]
   [[ 0 -1] [-1 -1]] [[ 0 -1] [-1 -1]]

   [[-1 -1] [-1  0]] [[-1  0] [-1 -1]]
   [[-1 -1] [ 1  0]] [[ 0 -1] [ 0  0]]
   [[-1 -1] [ 0  1]] [[-1  0] [ 0  0]]
   [[-1 -1] [ 0 -1]] [[ 0 -1] [-1 -1]]
   [[-1 -1] [-1  1]] [[-1  0] [-1  0]]
   [[-1 -1] [ 1  1]] [[ 0  0] [ 0  0]]
   [[-1 -1] [ 1 -1]] [[ 0 -1] [ 0 -1]]
   [[-1 -1] [-1 -1]] [[-1 -1] [-1 -1]]

   [[-1  0] [-1  0]] [[-1  0] [-1  0]]
   [[-1  0] [ 1  0]] [[ 0  0] [ 0  0]]
   [[-1  0] [ 0  1]] [[-1  1] [ 0  0]]
   [[-1  0] [ 0 -1]] [[-1 -1] [ 0  0]]
   [[-1  0] [-1  1]] [[-1  0] [-1  1]]
   [[-1  0] [ 1  1]] [[ 0  1] [ 0  0]]
   [[-1  0] [ 1 -1]] [[ 0 -1] [ 0  0]]
   [[-1  0] [-1 -1]] [[-1  0] [-1 -1]]})

(defn evaluate-single-move [relative-head-position move]
  (if (= move [0 0])
    [relative-head-position move]
    (if (= relative-head-position [0 0])
      [move [0 0]]
      (lookup-table [relative-head-position move]))))

(defn evaluate-rope-move [relative-knot-positions move]
  (loop [[relative-knot-position & xs] relative-knot-positions
         current-move move
         new-relative-knot-positions nil]
    (let [[new-relative-knot-pos next-movement] (evaluate-single-move relative-knot-position current-move)]
      (if (seq xs)
        (recur xs next-movement (conj new-relative-knot-positions new-relative-knot-pos))
        [(conj new-relative-knot-positions new-relative-knot-pos) next-movement]))))

(defn calc-tail-positions [knot-count lines]
  (->> lines
       (map read-instruction)
       (apply concat)
       (reduce (fn [[[tail-cood :as tail-history] relative-knot-positions] move]
                 (let [[new-relative-knot-positions tail-movement] (evaluate-rope-move relative-knot-positions move)]
                   [(conj tail-history (mapv + tail-cood tail-movement)) new-relative-knot-positions]))
               ['([0 0]) (repeat (dec knot-count) [0 0])])
       (first)))

(defn count-tail-positions [knot-count lines]
  (->> lines
       (calc-tail-positions knot-count)
       (distinct)
       (count)))

(defn part-1 []
  (utils/read-input-file 2022 9 (partial count-tail-positions 2)))

(defn part-2 []
  (utils/read-input-file 2022 9 (partial count-tail-positions 10)))