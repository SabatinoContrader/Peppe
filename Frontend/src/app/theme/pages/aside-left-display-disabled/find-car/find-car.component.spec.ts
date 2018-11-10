import { NO_ERRORS_SCHEMA } from "@angular/core";
import { FindCarComponent } from "./find-car.component";
import { ComponentFixture, TestBed } from "@angular/core/testing";

describe("FindCarComponent", () => {

  let fixture: ComponentFixture<FindCarComponent>;
  let component: FindCarComponent;
  beforeEach(() => {
    TestBed.configureTestingModule({
      schemas: [NO_ERRORS_SCHEMA],
      providers: [
      ],
      declarations: [FindCarComponent]
    });

    fixture = TestBed.createComponent(FindCarComponent);
    component = fixture.componentInstance;

  });

  it("should be able to create component instance", () => {
    expect(component).toBeDefined();
  });
  
});
