import { Component, OnInit } from '@angular/core';
import { PaymentService } from '../../services/payment.service';
import { Payment } from '../../models/Payment';

@Component({
    selector: 'app-payment',
    templateUrl: './payment.component.html',
    styleUrls: ['./payment.component.css']
})

export class PaymentComponent implements OnInit {
    paymentList: Payment[];

    constructor(private paymentService: PaymentService) { }

    ngOnInit() {
        this.paymentService.getpayments().subscribe(response => { this.paymentList = response });
    }

}
