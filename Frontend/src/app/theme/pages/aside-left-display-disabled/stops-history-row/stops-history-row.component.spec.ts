import { NO_ERRORS_SCHEMA } from "@angular/core";
import { StopsHistoryRowComponent } from "./stops-history-row.component";
import { ComponentFixture, TestBed } from "@angular/core/testing";

describe("StopsHistoryRowComponent", () => {

  let fixture: ComponentFixture<StopsHistoryRowComponent>;
  let component: StopsHistoryRowComponent;
  beforeEach(() => {
    TestBed.configureTestingModule({
      schemas: [NO_ERRORS_SCHEMA],
      providers: [
      ],
      declarations: [StopsHistoryRowComponent]
    });

    fixture = TestBed.createComponent(StopsHistoryRowComponent);
    component = fixture.componentInstance;

  });

  it("should be able to create component instance", () => {
    expect(component).toBeDefined();
  });
  
});
