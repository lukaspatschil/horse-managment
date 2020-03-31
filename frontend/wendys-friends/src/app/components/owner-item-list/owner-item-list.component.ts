import { Component, OnInit, Input } from '@angular/core';
import { OwnerService } from "../../service/owner.service";

import { Owner } from "src/app/dto/owner";

@Component({
  selector: 'app-owner-item-list',
  templateUrl: './owner-item-list.component.html',
  styleUrls: ['./owner-item-list.component.scss']
})
export class OwnerItemListComponent implements OnInit {
  @Input() owner: Owner;

  constructor() { }

  ngOnInit(): void {
  }

}
