export class Payment {

    id: number;
    quantity: number;
    id_stop: number;
    username: string;

    constructor(id: number, quantity: number, id_stop: number, username: string) {
        this.id = id;
        this.quantity = quantity;
        this.id_stop = id_stop;
        this.username = username;
    }
}
