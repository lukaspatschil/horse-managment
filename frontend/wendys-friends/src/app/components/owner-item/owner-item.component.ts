import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { OwnerService } from '../../service/owner.service';

import { Owner } from 'src/app/dto/owner';

@Component({
  selector: 'app-owner-item',
  templateUrl: './owner-item.component.html',
  styleUrls: ['./owner-item.component.scss']
})
export class ListOwnerComponent implements OnInit {
  @Input() owner: Owner;
  @Output() deleteOwner: EventEmitter<Owner> = new EventEmitter();
  @Output() updateOwner: EventEmitter<Owner> = new EventEmitter();

  show:boolean;
  name:string;

  constructor(private ownerService:OwnerService) { }

  ngOnInit(): void {
    this.show = false;
    this.name = this.owner.name;
  }

  onDelete(owner) {
    this.deleteOwner.emit(owner);
  }

  onShow() {
    this.show = !this.show;
  }

  onUpdate() {
    this.owner.name = this.name;
    console.log(this.owner);
    this.updateOwner.emit(this.owner);
  }

}
