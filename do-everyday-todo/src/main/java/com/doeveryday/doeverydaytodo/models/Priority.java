package com.doeveryday.doeverydaytodo.models;

/**
 * Value label is name of bootstrap classes
 */

public enum Priority {
        NORMAL(""),
        WARING("list-group-item-warning"),
        IMPORTANT("list-group-item-primary"),
        VERY_IMPORTANT("list-group-item-danger");

        public final String label;

        Priority(String label) {
            this.label = label;
        }
}
