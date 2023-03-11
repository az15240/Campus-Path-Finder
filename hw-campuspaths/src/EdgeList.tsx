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
    textValueStart: string; // to store the start short name
    textValueEnd: string;   // to store the end short name
}

/**
 * A text field that allows the user to enter the list of edges.
 * Also contains the buttons that the user will use to interact with the app.
 */
class EdgeList extends Component<EdgeListProps, EdgeListState> {

    constructor(props: EdgeListProps) {
        super(props);
        this.state = {
            textValueStart: "",
            textValueEnd: "",
        };
    }

    textChangeStart(event: any) {
        this.setState({
            textValueStart: event.target.value,
        });
    }

    textChangeEnd(event: any) {
        this.setState({
            textValueEnd: event.target.value,
        });
    }

    onClicked = async () => {
        try {
            let fetchText = "http://localhost:4567/campus-paths?start=".concat(this.state.textValueStart).concat("&end=").concat(this.state.textValueEnd);
            let response = await fetch(fetchText);
            if (!response.ok) {
                alert("The status is wrong! Expected: 200, Was: " + response.status);
                return; // Don't keep trying to execute if the response is bad.
            }
            let paths = (await response.json()).path; // get the path field in Path<Point>, that contains a list of Points
            let tempEdges: ColoredEdge[] = []; // to store all new edges and update the edges only once
            let counter = 0;// to count for keys in edges
            for (let p of paths) {
                let temp: ColoredEdge = {
                    x1: p.start.x,
                    y1: p.start.y,
                    x2: p.end.x,
                    y2: p.end.y,
                    color: "red",
                    key: counter.toString(),
                }
                counter++;
                tempEdges.push(temp); // add the edge to the list of edges
            }
            this.props.onChange(tempEdges); // send the edge list back to the App
        } catch (e) {
            alert("There was an error contacting the server.");
            console.log(e);
        } finally { // always execute this: to clear the two text fields
            this.setState({
                textValueStart: "",
                textValueEnd: "",
            })
        }
    };

    onCleared() { // onClick for Clear button
        this.setState({
            textValueStart: "",
            textValueEnd: "",
        });
        this.props.onChange([]);
    }

    render() {
        return (
            <div id="edge-list">
                <b>Input the Start and End names below.</b><br/><br/>
                Input format for both text boxes: a short name for a campus building.<br/>
                Input the start name at the first text box, then input the end name at the second text box.<br/>
                Leading or trailing spaces <b>are allowed</b>.<br/>
                (Scrow down to see a table of short name and long name correspondences.)<br/>
                <br/>
                An alert saying error message 400 means either of your two inputs are invalid short names.<br/>
                <br/>
                <textarea id="start-text-area"
                          rows={1}
                          cols={30}
                          placeholder={"Input start here..."}
                          onChange={(event) => {
                              this.textChangeStart(event);
                              console.log('start-text-area onChange was called');
                          }}
                    // value={"I'm stuck..."}
                          value={this.state.textValueStart}
                /> <br/>
                <textarea id="end-text-area"
                          rows={1}
                          cols={30}
                          placeholder={"Input end here..."}
                          onChange={(event) => {
                              this.textChangeEnd(event);
                              console.log('end-text-area onChange was called');
                          }}
                          value={this.state.textValueEnd}
                /> <br/>
                <button onClick={() => {
                    this.onClicked();
                    console.log('Draw onClick was called');
                }}>Draw
                </button>
                <button onClick={() => {
                    this.onCleared();
                    console.log('Clear onClick was called');
                }}>Clear
                </button>
                <br/><br/>
                <hr/>

                Correspondence table of short names to long names: <br/><br/>

                <table className="center">
                    <tr>
                        <th>Short Names</th>
                        <th>Long Names</th>
                    </tr>
                    <tr>
                        <td>BAG</td>
                        <td>Bagley Hall (East Entrance)</td>
                    </tr>
                    <tr>
                        <td>BAG (NE)</td>
                        <td>Bagley Hall (Northeast Entrance)</td>
                    </tr>
                    <tr>
                        <td>BGR</td>
                        <td>By George</td>
                    </tr>
                    <tr>
                        <td>CSE</td>
                        <td>Paul G. Allen Center for Computer Science & Engineering</td>
                    </tr>
                    <tr>
                        <td>CS2</td>
                        <td>Bill & Melinda Gates Center For Computer Science & Engineering</td>
                    </tr>
                    <tr>
                        <td>DEN</td>
                        <td>Denny Hall</td>
                    </tr>
                    <tr>
                        <td>EEB</td>
                        <td>Electrical Engineering Building (North Entrance)</td>
                    </tr>
                    <tr>
                        <td>EEB (S)</td>
                        <td>Electrical Engineering Building (South Entrance)</td>
                    </tr>
                    <tr>
                        <td>GWN</td>
                        <td>Gowen Hall</td>
                    </tr>
                    <tr>
                        <td>KNE</td>
                        <td>Kane Hall (North Entrance)</td>
                    </tr>
                    <tr>
                        <td>KNE (E)</td>
                        <td>Kane Hall (East Entrance)</td>
                    </tr>
                    <tr>
                        <td>KNE (SE)</td>
                        <td>Kane Hall (Southeast Entrance)</td>
                    </tr>
                    <tr>
                        <td>KNE (S)</td>
                        <td>Kane Hall (South Entrance)</td>
                    </tr>
                    <tr>
                        <td>KNE (SW)</td>
                        <td>Kane Hall (Southwest Entrance)</td>
                    </tr>
                    <tr>
                        <td>LOW</td>
                        <td>Loew Hall</td>
                    </tr>
                    <tr>
                        <td>MGH</td>
                        <td>Mary Gates Hall (North Entrance)</td>
                    </tr>
                    <tr>
                        <td>MGH (E)</td>
                        <td>Mary Gates Hall (East Entrance)</td>
                    </tr>
                    <tr>
                        <td>MGH (S)</td>
                        <td>Mary Gates Hall (South Entrance)</td>
                    </tr>
                    <tr>
                        <td>MGH (SW)</td>
                        <td>Mary Gates Hall (Southwest Entrance)</td>
                    </tr>
                    <tr>
                        <td>MLR</td>
                        <td>Miller Hall</td>
                    </tr>
                    <tr>
                        <td>MOR</td>
                        <td>Moore Hall</td>
                    </tr>
                    <tr>
                        <td>MUS</td>
                        <td>Music Building (Northwest Entrance)</td>
                    </tr>
                    <tr>
                        <td>MUS (E)</td>
                        <td>Music Building (East Entrance)</td>
                    </tr>
                    <tr>
                        <td>MUS (SW)</td>
                        <td>Music Building (Southwest Entrance)</td>
                    </tr>
                    <tr>
                        <td>MUS (S)</td>
                        <td>Music Building (South Entrance)</td>
                    </tr>
                    <tr>
                        <td>OUG</td>
                        <td>Odegaard Undergraduate Library</td>
                    </tr>
                    <tr>
                        <td>PAA</td>
                        <td>Physics/Astronomy Building A</td>
                    </tr>
                    <tr>
                        <td>PAB</td>
                        <td>Physics/Astronomy Building</td>
                    </tr>
                    <tr>
                        <td>SAV</td>
                        <td>Savery Hall</td>
                    </tr>
                    <tr>
                        <td>SUZ</td>
                        <td>Suzzallo Library</td>
                    </tr>
                    <tr>
                        <td>T65</td>
                        <td>Thai 65</td>
                    </tr>
                    <tr>
                        <td>FSH</td>
                        <td>Fishery Sciences Building</td>
                    </tr>
                    <tr>
                        <td>MCC</td>
                        <td>McCarty Hall (Main Entrance)</td>
                    </tr>
                    <tr>
                        <td>MCC (S)</td>
                        <td>McCarty Hall (South Entrance)</td>
                    </tr>
                    <tr>
                        <td>UBS</td>
                        <td>University Bookstore</td>
                    </tr>
                    <tr>
                        <td>UBS (Secret)</td>
                        <td>University Bookstore (Secret Entrance)</td>
                    </tr>
                    <tr>
                        <td>RAI</td>
                        <td>Raitt Hall (West Entrance)</td>
                    </tr>
                    <tr>
                        <td>RAI (E)</td>
                        <td>Raitt Hall (East Entrance)</td>
                    </tr>
                    <tr>
                        <td>ROB</td>
                        <td>Roberts Hall</td>
                    </tr>
                    <tr>
                        <td>CHL</td>
                        <td>Chemistry Library (West Entrance)</td>
                    </tr>
                    <tr>
                        <td>CHL (NE)</td>
                        <td>Chemistry Library (Northeast Entrance)</td>
                    </tr>
                    <tr>
                        <td>CHL (SE)</td>
                        <td>Chemistry Library (Southeast Entrance)</td>
                    </tr>
                    <tr>
                        <td>IMA</td>
                        <td>Intramural Activities Building</td>
                    </tr>
                    <tr>
                        <td>HUB</td>
                        <td>Student Union Building (Main Entrance)</td>
                    </tr>
                    <tr>
                        <td>HUB (West Food)</td>
                        <td>Student Union Building (West Food Entrance)</td>
                    </tr>
                    <tr>
                        <td>HUB (South Food)</td>
                        <td>Student Union Building (South Food Entrance)</td>
                    </tr>
                    <tr>
                        <td>MNY</td>
                        <td>Meany Hall (Northeast Entrance)</td>
                    </tr>
                    <tr>
                        <td>MNY (NW)</td>
                        <td>Meany Hall (Northwest Entrance)</td>
                    </tr>
                    <tr>
                        <td>PAR</td>
                        <td>Parrington Hall</td>
                    </tr>
                    <tr>
                        <td>MCM</td>
                        <td>McMahon Hall (Northwest Entrance)</td>
                    </tr>
                    <tr>
                        <td>MCM (SW)</td>
                        <td>McMahon Hall (Southwest Entrance)</td>
                    </tr>
                    <tr>
                        <td>CMU</td>
                        <td>Communications Building</td>
                    </tr>
                </table>
            </div>
        );
    }
}

export default EdgeList;
