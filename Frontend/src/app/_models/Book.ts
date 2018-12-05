export class Book {

    id: number;
    username: string;
    id_slot: number;
    id_payment: number;
    quantity: number;
    id_stop: number;
    start: any;
    timeToAdd: number;
    id_car: number;

    constructor(id: number, username: string, id_slot: number, id_payment: number,quantity: number,id_stop: number,start: any,timeToAdd: number, id_car: number) {
        this.id = id;
        this.username = username;
        this.id_slot = id_slot;
        this.id_payment = id_payment;
        this.quantity = quantity;
        this.id_stop = id_stop;
        this.start = start;
        this.timeToAdd = timeToAdd;
        this.id_car=id_car;
    }
}