export interface Student {
  id: string,
  code: string,
  firstName: string,
  lastName: string,
  email:string,
  programId: string,
  photo: string,
}

export interface Payment {
  id: number,
  date: string,
  amount: string,
  type: string,
  status: string,
  file: string,
}

export enum PaymentType {
  CASH,CHECK,TRANSFER,DEPOSIT
}
export enum PaymentStatus {
  CREATED,VALIDATED,REJECTED
}
