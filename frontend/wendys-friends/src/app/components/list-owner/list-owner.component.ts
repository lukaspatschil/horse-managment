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

  constructor(private ownerService:OwnerService) { }

  ngOnInit(): void {
  }

  onDelete(owner) {
    this.deleteOwner.emit(owner);
  }

}
