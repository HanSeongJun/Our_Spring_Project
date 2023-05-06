import { Rating } from '@mui/material';


const Grade = () => {
    let icon;

    const grade = 4;//backend에서 넘어온 날씨 데이터(하늘상태와 강수형태)
    const grade2 = 1;//backend에서 넘어온 미세먼지 데이터(미세먼지)


    return (
        <div className="Grade">

            <div className="Weather">
                <p>오늘의 하늘상태와 강수형태는?</p>
                <Rating
                    name="Weather-grade"
                    value={grade}
                    readOnly
                    iconFilled={icon}
                    iconEmpty='🌈'
                    iconHovered={icon}
                />

            </div>
            <div className="ParticulateMater">
                <p>서울기준 오늘의 미세먼지는?</p>
                <Rating
                    name="ParticulateMater-grade"
                    value={grade2}
                    readOnly
                    iconFilled={icon}
                    iconEmpty='😐'
                    iconHovered={icon}
                />

            </div>


        </div>

    );
};

export default Grade;