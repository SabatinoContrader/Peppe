export class Report{

    type: number;
    description: string;
    time: string;
    username: string;
    state: number;

    constructor(type: number, description: string, time: string, username: string, state: number){
        this.type = type;
        this.description = description;
        this.time = time;
        this.username = username;
        this.state = state;
    }


}