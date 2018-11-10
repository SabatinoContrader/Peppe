import { Component, Renderer, OnInit, NgZone, Output } from '@angular/core';
import { Payment } from '../../../../_models/Payment';
import { Transaction } from '../../../../_models/Transaction';
import { PaymentService } from '../../../../_services/payment.service';

//import { Wallet } from '../../models/Wallet';
//import { AllPayment } from 'src/models/AllPayment';

@Component({
    selector: 'app-payment',
    templateUrl: './payment.component.html',
    styleUrls: ['./payment.component.scss']
})

export class PaymentComponent implements OnInit {
    paymentList: Payment[];
    paymentToken: string;
    globalListener: any;
    wallet: any;
    price: number;

    constructor(private paymentService: PaymentService, private renderer: Renderer, private _zone: NgZone) { }

    ngOnInit() {
        this.paymentService.getpayments().subscribe(response => { this.paymentList = response.payment_list });
        this.paymentService.getwallet().subscribe(response => { this.wallet = response });
    }


}


