import { Component, OnInit, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-add-owner',
  templateUrl: './add-owner.component.html',
  styleUrls: ['./add-owner.component.scss']
})
export class AddOwnerComponent implements OnInit {
  @Output() addOwner: EventEmitter<any> = new EventEmitter();

  name:string;

  constructor() { }

  ngOnInit(): void {
  }

  
  onSubmit() {
    const owner = {
      name: this.name
    }
    console.log(owner);
    this.addOwner.emit(owner);
  }
}
