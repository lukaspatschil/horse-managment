import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { OwnerService } from '../../service/owner.service';

import { Owner } from 'src/app/dto/owner';

@Component({
  selector: 'app-list-owner',
  templateUrl: './list-owner.component.html',
  styleUrls: ['./list-owner.component.scss']
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
