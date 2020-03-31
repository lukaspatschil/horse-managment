import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HorseItemListComponent } from './horse-item-list.component';

describe('HorseItemListComponent', () => {
  let component: HorseItemListComponent;
  let fixture: ComponentFixture<HorseItemListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HorseItemListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HorseItemListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
