(ns clojure-noob.entities
  (:require [clojure-noob.actions :refer [update-player]]))

(def init-baddies
  [{:x 20
    :y 20
    :type :baddie}])

(def init-statics
  [{:x 30
    :y 30
    :type :wall}])

(def init-player
  {:x 10
   :y 10
   :type :player
   :actions [update-player]})

(def initial-state
  {:turn 1
   :tiles (conj (concat init-baddies init-statics) init-player)})
