import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { HorseService } from '../../service/horse.service';

import { Horse } from 'src/app/dto/horse';
import { Owner } from 'src/app/dto/owner';

@Component({
  selector: 'app-list-horse',
  templateUrl: './list-horse.component.html',
  styleUrls: ['./list-horse.component.scss']
})
export class ListHorseComponent implements OnInit {
  @Input() owners: Owner[];
  @Input() horse: Horse;
  @Output() deleteHorse: EventEmitter<Horse> = new EventEmitter();
  @Output() updateHorse: EventEmitter<Horse> = new EventEmitter();

  show:boolean;
  name:string;
  notes:string;
  rating:number;
  birthday:string;
  owner:number;
  race:string;

  constructor(private horseService:HorseService) { }

  ngOnInit(): void {
    this.name = this.horse.name;
    this.notes = this.horse.notes;
    this.rating = this.horse.rating;
    this.birthday = this.horse.birthday;
    console.log(this.horse.race);
    this.race = this.horse.race;
    console.log(this.horse.owner);
    this.owner = this.horse.owner;
  }

  onDelete(horse) {
    this.deleteHorse.emit(horse);
  }

  onShow() {
    this.show = !this.show;
  }

  onUpdate() {
    this.horse.name = this.name;
    this.horse.notes = this.notes;
    this.horse.rating = this.rating;
    this.horse.birthday = this.birthday;
    this.horse.race = this.race;
    console.log(this.horse);
    this.updateHorse.emit(this.horse);
  }

}
