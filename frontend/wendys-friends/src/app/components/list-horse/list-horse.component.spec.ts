import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListHorseComponent } from './list-horse.component';

describe('ListHorseComponent', () => {
  let component: ListHorseComponent;
  let fixture: ComponentFixture<ListHorseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListHorseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListHorseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
