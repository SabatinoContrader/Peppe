import { BookService } from "./book.service";
import { TestBed } from "@angular/core/testing";

describe("BookService", () => {

  let service: BookService;
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        BookService
      ]
    });
    service = TestBed.get(BookService);

  });

  it("should be able to create service instance", () => {
    expect(service).toBeDefined();
  });

});
