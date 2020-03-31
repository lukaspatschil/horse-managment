import { Component, OnInit, Input, EventEmitter, Output} from '@angular/core';
import { HorseService } from '../../service/horse.service';

import { Horse } from 'src/app/dto/horse';
import { Owner } from 'src/app/dto/owner';

@Component({
  selector: 'app-horse-item',
  templateUrl: './horse-item.component.html',
  styleUrls: ['./horse-item.component.scss']
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
  image: string;
  type: string;
  imageFile: File;
  imageEncode: string;
  fileList: FileList;

  constructor(private horseService:HorseService) { }

  ngOnInit(): void {
    this.name = this.horse.name;
    this.notes = this.horse.notes;
    this.rating = this.horse.rating;
    this.birthday = this.horse.birthday;
    this.race = this.horse.race;
    this.owner = this.horse.owner;
    this.type = this.horse.type;
    this.imageEncode = this.horse.image;
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
    this.horse.owner = this.owner;
    this.horse.image = this.imageEncode;
    this.horse.type = this.type;
    console.log(this.horse);
    this.updateHorse.emit(this.horse);
  }

  picked(event: any) {
    const fileList: FileList = event.target.files;
    if (fileList.length > 0) {
      const file: File = fileList[0];
      this.imageFile = file;
      this.handleInputChange(file); // turn into base64
    } else {
      alert("No file selected");
    }
  }

  handleInputChange(files: any) {
    const file = files;
    const reader = new FileReader();

    reader.onloadend = this._handleReaderLoaded.bind(this);
    reader.readAsDataURL(file);
  }
  _handleReaderLoaded(e: any) {
    const reader = e.target;
    const base64result = reader.result.substr(reader.result);

    this.image = base64result;
    this.type = base64result.substr(0, base64result.indexOf(",") + 1);
    this.imageEncode = base64result.substr(base64result.indexOf(",") + 1);
  }
}
