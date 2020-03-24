import { Component, OnInit, EventEmitter, Output, Input } from '@angular/core';
import { not } from '@angular/compiler/src/output/output_ast';
import { OwnerService } from '../../service/owner.service';

import { Owner } from 'src/app/dto/owner';

@Component({
  selector: 'app-add-horse',
  templateUrl: './add-horse.component.html',
  styleUrls: ['./add-horse.component.scss']
})
export class AddHorseComponent implements OnInit {
  @Input() owners: Owner[];
  @Output() addHorse: EventEmitter<any> = new EventEmitter();

  name:string;
  notes:string;
  rating:number;
  birthday:string;
  owner:number;
  race:string;

  constructor(private ownerService:OwnerService) { }

  ngOnInit(): void {
    this.rating = 0;
    this.owner = 0;
    this.race= "0";
  }

  onSubmit() {
    if (this.notes == null) {
      this.notes = "";
    }
    const horse = {
      name: this.name,
      notes: this.notes,
      rating: this.rating,
      birthday: this.birthday,
      owner: this.owner,
      race: this.race
    }

    console.log(horse);

    this.addHorse.emit(horse);
  }

}
