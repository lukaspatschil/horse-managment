export class Horse {
    constructor(
      public id: number,
      public name: string,
      public notes: string,
      public rating: number,
      public birthday: string,
      public owner: number,
      public race: string,
      public image: string,
      public type:string,
      public createdAt:string,
      public updatedAt:string) {
    }
  }