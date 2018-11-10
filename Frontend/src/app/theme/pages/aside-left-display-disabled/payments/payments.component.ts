import { Component, Renderer, OnInit, NgZone, Output } from '@angular/core';
import { Payment } from '../../../../_models/Payment';
import { Transaction } from '../../../../_models/Transaction';
import { PaymentService } from '../../../../_services/payment.service';

@Component({
  selector: "app-payments",
  templateUrl: "./payments.component.html",
  styleUrls: ["./payments.component.scss"]
})

export class PaymentsComponent implements OnInit {

    transactionList: Transaction[];
    paymentToken: string;
    globalListener: any;
    wallet: any;
    price: number;

    constructor(private paymentService: PaymentService, private renderer: Renderer, private _zone: NgZone) { }

    ngOnInit() {
        this.paymentService.getpayments().subscribe(response => { this.transactionList = response.transaction_list });
            }


}
