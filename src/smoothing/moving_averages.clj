(ns smoothing.moving-averages
  (:require [clojure.algo.generic.math-functions :as math]))

(defn- average [lst] (/ (reduce + lst) (count lst)))
(defn simple-moving-average [window lst] (map average (partition window 1 lst)))

(defn- weighted-average [power lst]
  (let [indices (map (partial + 1) (range (count lst)))
	total (reduce #(+ %1 (math/pow %2 power)) 0 indices)
	zipped-list (map vector indices lst)]
    (reduce #(+ (/ (* (second %2) (math/pow (first %2) power)) total) %1) 0 zipped-list)))
(defn weighted-moving-average [window lst & power] (map (partial weighted-average
							       (if (nil? power) 1 (first power)))
						      (partition window 1 lst)))

(defn- exponentially-weighted-average [lst alpha]
  (reductions #(+ (* alpha %2) (* (- 1 alpha) %1)) (first lst) (rest lst)))
(defn exponential-moving-average [lst alpha] (exponentially-weighted-average lst alpha))
