/*
 * Copyright (C) 2022 Kevin Zatloukal and James Wilcox.  All rights reserved.  Permission is
 * hereby granted to students registered for University of Washington
 * CSE 331 for use solely during Autumn Quarter 2022 for purposes of
 * the course.  No other use, copying, distribution, or modification
 * is permitted without prior written consent. Copyrights for
 * third-party components of this work must be honored.  Instructors
 * interested in reusing these course materials should contact the
 * author.
 */

import React, {Component} from 'react';
import {ColoredEdge} from "./types";

interface EdgeListProps {
    onChange(edges: ColoredEdge[]): void;  // called when a new edge list is ready
}

interface EdgeListState {
    textValue: string;
}

/**
 * A text field that allows the user to enter the list of edges.
 * Also contains the buttons that the user will use to interact with the app.
 */
class EdgeList extends Component<EdgeListProps, EdgeListState> {

    constructor(props: EdgeListProps) {
        super(props);
        this.state = {
            textValue: "",
        };
    }

    textChange(event: any) {
        this.setState({
            textValue: event.target.value,
        });
    }

    onClicked() {

        let alertMessage: string[] = [];
        let tempEdges: ColoredEdge[] = [];

        let lines = this.state.textValue.split("\n");
        for (let i = 0; i < lines.length; i++) {
            let currentLine: string = lines[i];
            let arr = currentLine.split(' ');
            if (arr.length === 5) {
                let _x1 = parseInt(arr[0]);
                let _y1 = parseInt(arr[1]);
                let _x2 = parseInt(arr[2]);
                let _y2 = parseInt(arr[3]);
                let _color = arr[4];
                if (_x1 >= 0 && _x1 <= 4000 && _y1 >= 0 && _y1 <= 4000 && _x2 >= 0 && _x2 <= 4000 && _y2 >= 0 && _y2 <= 4000) {
                    alertMessage[i] = `Line ${i}: Correct.`;
                    let temp: ColoredEdge = {
                        x1: _x1,
                        y1: _y1,
                        x2: _x2,
                        y2: _y2,
                        color: _color,
                        key: i.toString(),
                    }
                    tempEdges.push(temp);
                    // this.props.onChange(temp);
                    this.setState({
                        textValue: "",
                    })
                } else {
                    alertMessage[i] = `Line ${i}: Wrong number format or coordinates out of range!`;
                    this.setState({
                        textValue: "",
                    })
                }

            } else {
                alertMessage[i] = `Line ${i}: Wrong number of elements! Notice that your five arguments must be separated by a SINGLE space!`;

                this.setState({
                    textValue: "",
                })
            }
        }
        let alertString: string = alertMessage.join("\n");
        alert(alertString);
        this.props.onChange(tempEdges);
    }

    onCleared() {
        this.setState({
            textValue: "",
        });
    }

    render() {
        return (
            <div id="edge-list">
                Edges<br/>
                Edge input format: x1 y1 x2 y2 COLOR. They should be separated by a SINGLE space.<br/>
                Range of x1, y1, x2, y2 should be numbers between 0 and 4000, inclusive.<br/>
                <textarea
                    rows={5}
                    cols={30}
                    onChange={(event) => {
                        this.textChange(event);
                        console.log('textarea onChange was called');}}
                    // value={"I'm stuck..."}
                    value={this.state.textValue}
                /> <br/>
                <button onClick={() => {
                    this.onClicked();
                    console.log('Draw onClick was called');}}>Draw</button>
                <button onClick={() => {
                    this.onCleared();
                    console.log('Clear onClick was called');}}>Clear</button>
            </div>
        );
    }
}

export default EdgeList;
