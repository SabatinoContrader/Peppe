export class Transaction
{
    id: number;
    quantity: number;
    date: Date;
    card_number: number;
    username: string;

    constructor(id: number, quantity: number, date: Date, card_number: number, username: string)
    {
        this.id = id;
        this.quantity = quantity;
        this.date = date;
        this.card_number = card_number;
        this.username = username;
    }
}