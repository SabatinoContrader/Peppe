export class Transaction {

    id: number;
    value: number;
    date: Date;
    card_number: number;   
 
    constructor(id: number, value: number, date: Date, card_number: number) {
        this.id = id;
        this.value = value;
        this.date = date;
        this.card_number = card_number;
    }
}
