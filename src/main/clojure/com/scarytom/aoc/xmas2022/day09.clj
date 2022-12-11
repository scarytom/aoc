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
  {[[-1  1] [-1  0]] [[-1  0] [-1  1]]
   [[-1  1] [ 1  0]] [[ 0  1] [ 0  0]]
   [[-1  1] [ 0  1]] [[ 0  1] [-1  1]]
   [[-1  1] [ 0 -1]] [[-1  0] [ 0  0]]

   [[ 0  1] [-1  0]] [[-1  1] [ 0  0]]
   [[ 0  1] [ 1  0]] [[ 1  1] [ 0  0]]
   [[ 0  1] [ 0  1]] [[ 0  1] [ 0  1]]
   [[ 0  1] [ 0 -1]] [[ 0  0] [ 0  0]]

   [[ 1  1] [-1  0]] [[ 0  1] [ 0  0]]
   [[ 1  1] [ 1  0]] [[ 1  0] [ 1  1]]
   [[ 1  1] [ 0  1]] [[ 0  1] [ 1  1]]
   [[ 1  1] [ 0 -1]] [[ 1  0] [ 0  0]]

   [[ 1  0] [-1  0]] [[ 0  0] [ 0  0]]
   [[ 1  0] [ 1  0]] [[ 1  0] [ 1  0]]
   [[ 1  0] [ 0  1]] [[ 1  1] [ 0  0]]
   [[ 1  0] [ 0 -1]] [[ 1 -1] [ 0  0]]

   [[ 1 -1] [-1  0]] [[ 0 -1] [ 0  0]]
   [[ 1 -1] [ 1  0]] [[ 1  0] [ 1 -1]]
   [[ 1 -1] [ 0  1]] [[ 1  0] [ 0  0]]
   [[ 1 -1] [ 0 -1]] [[ 0 -1] [ 1 -1]]

   [[ 0 -1] [-1  0]] [[-1 -1] [ 0  0]]
   [[ 0 -1] [ 1  0]] [[ 1 -1] [ 0  0]]
   [[ 0 -1] [ 0  1]] [[ 0  0] [ 0  0]]
   [[ 0 -1] [ 0 -1]] [[ 0 -1] [ 0 -1]]

   [[-1 -1] [-1  0]] [[-1  0] [-1 -1]]
   [[-1 -1] [ 1  0]] [[ 0 -1] [ 0  0]]
   [[-1 -1] [ 0  1]] [[-1  0] [ 0  0]]
   [[-1 -1] [ 0 -1]] [[ 0 -1] [-1 -1]]

   [[-1  0] [-1  0]] [[-1  0] [-1  0]]
   [[-1  0] [ 1  0]] [[ 0  0] [ 0  0]]
   [[-1  0] [ 0  1]] [[-1  1] [ 0  0]]
   [[-1  0] [ 0 -1]] [[-1 -1] [ 0  0]]})

(defn evaluate-move [relative-head-position move]
  (if (= relative-head-position [0 0])
    [move [0 0]]
    (lookup-table [relative-head-position move])))

(defrecord Tree [height position]
  Comparable
  (compareTo [this other]
    (.compareTo (:height this) (:height other))))

(defn count-tail-positions [lines]
  (->> lines
       (map read-instruction)
       (apply concat)
       (reduce (fn [[[tail-cood :as tail-history] relative-head-position] move]
                 (let [[new-relative-head-pos tail-movement] (evaluate-move relative-head-position move)]
                   [(conj tail-history (mapv + tail-cood tail-movement)) new-relative-head-pos]))
               ['([0 0]) [0 0]])
       (first)
       (distinct)
       (count)))

(defn part-1 []
  (utils/read-input-file 2022 9 count-tail-positions))

(defn part-2 []
  (utils/read-input-file 2022 9 identity))