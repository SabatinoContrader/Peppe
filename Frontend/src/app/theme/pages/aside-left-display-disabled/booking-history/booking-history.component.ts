import { Component, OnInit } from '@angular/core';
import { BookService } from './../../../../_services/book.service';
import { Book } from '../../../../_models/Book';

@Component({
    selector: 'app-booking-history',
    templateUrl: './booking-history.component.html',
    styleUrls: ['./booking-history.component.scss']
})

export class BookingHistoryComponent implements OnInit {

    public booksList: Array<Book>;
   
    constructor(
        private bookService: BookService
    ) { }

    ngOnInit() {
        this.bookService.getAllBooks().subscribe(response => { this.booksList = response });
      
    }

    
}