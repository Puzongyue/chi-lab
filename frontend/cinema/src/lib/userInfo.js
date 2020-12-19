export let userInfo = {
    name: "家里有机群",
    gender: 2,
    birthday: new Date("1999-02-14"),
    profession: "学生",
    signature: "再见了妈妈今晚我就要远航！"
}
export function updateInfo (newUserInfo){
    userInfo = newUserInfo
}