

export class Stop {
    id_stop: number;
    address: string;
    start: Date;
    finish: Date;
    name: string;
    price: number;
    //servono?
//    id_car: number;
//    id_slot: number;

    constructor(id: number, address: string, start: Date, finish: Date, name:string, price: number){
            this.id_stop = id;
            this.address = address;
            this.start = start;
            this.finish = finish;
            this.name = name;
            this.price = price;
//            this.id_car = id_car;
//            this.id_slot = id_slot;

    }
}