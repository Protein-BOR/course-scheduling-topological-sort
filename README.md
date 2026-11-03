
# Course Scheduling Using Topological Ordering

A Java application that generates a valid course completion order based on prerequisite dependencies using graph algorithms.

## Overview
This project models university courses and their prerequisites as a directed graph. It uses Depth-First Search (DFS) and topological sorting to determine a course sequence that satisfies prerequisite requirements.

## Technologies
- Java
- Graph Data Structures
- Depth-First Search (DFS)
- Topological Sorting
- LinkedHashMap

## Features
- Represents courses and prerequisites as a directed graph
- Uses adjacency lists for graph representation
- Applies DFS-based topological sorting
- Generates an ordering that respects prerequisite dependencies
- Processes a dataset of 29 courses and 30 prerequisite relationships

## How It Works
Each course is represented as a vertex, and each prerequisite relationship is represented as a directed edge.

For example, CS 1411 → CS 1412 means CS 1411 must be completed before CS 1412.

The algorithm traverses the graph using DFS and generates a topological ordering.

## Contributors
- Samir Chand
- Prayatna Bhatta

## Academic Project
Developed as part of CS 3364 — Design and Analysis of Algorithms at Texas Tech University.
