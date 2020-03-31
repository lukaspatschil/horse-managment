import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OwnerItemListComponent } from './owner-item-list.component';

describe('OwnerItemListComponent', () => {
  let component: OwnerItemListComponent;
  let fixture: ComponentFixture<OwnerItemListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OwnerItemListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OwnerItemListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
