package com.example.priorityChannel;

public enum Priority {
    LOW(0),
    MEDIUM(1),
    HIGH(2),
    CRITICAL(3);
    private int weight;

    private Priority(int weight){
        this.weight=weight;
    }
}
