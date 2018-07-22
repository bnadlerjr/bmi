(ns bmi.core
  (:require [reagent.core :as reagent]))

(defn bmi-component
  []
  (let [state (reagent/atom {:height 180 :weight 80})]
    (fn []
      [:div
       [:h1 "BMI Calculator"]
       [:div "Height: " (:height @state) "cm"
        [:input {:type "range"
                 :value (:height @state)
                 :min 100
                 :max 220
                 :on-change (fn [e]
                              (swap! state assoc :height (.-target.value e)))}]]
       [:div "Weight: " (:weight @state) "kg"
        [:input {:type "range"
                 :value (:weight @state)
                 :min 30
                 :max 150
                 :on-change (fn [e]
                              (swap! state assoc :weight (.-target.value e)))}]]])))

(defn start
  []
  (reagent/render [bmi-component]
                  (.getElementById js/document "bmi-component")))

(defn stop
  [])

(defn ^:export init
  []
  (start))
