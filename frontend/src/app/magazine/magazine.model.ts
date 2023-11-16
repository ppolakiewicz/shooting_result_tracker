export interface MagazineDto {
  id: string,
  name: string,
  capacity: number,
  weaponCount: number,
  ammunition: Set<Ammunition>,
  creationDate: Date
}

export interface Ammunition {
  caliber: string,
  quantity: number
}

export interface MagazineCreateCommand {
  name: string
}
