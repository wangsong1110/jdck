package com.jdck.data.common;

/**
 * @Description 北斗云 倾角传感器 合倾角，倾斜方向 计算公式
 * @Author zyc
 * @Date 2020/8/1 10:58
 */
public class PilingUtils {

    /**
     * 计算倾斜角度
     *
     * @param pitch
     * @param roll
     * @return
     */
    public static double tiltAngle(double pitch, double roll) {
        if (pitch == -1 && roll == -1) {
            pitch = 0;
        }
        if (roll == -1) {
            roll = 0;
        }
        double angle = Math.toDegrees(Math.acos(Math.cos(Math
                .toRadians(pitch))
                * Math.cos(Math.toRadians(roll))));
        return angle;
    }

    /**
     * 计算倾斜方向
     *
     * @param roll
     * @param pitch
     * @param heading
     * @return
     */
    public static double shadowHeading(double pitch, double roll, double heading) {
        if (pitch == -1 && roll == -1) {
            pitch = 0;
        }
        if (roll == -1) {
            roll = 0;
        }
        if (heading == -1) {
            heading = 0;
        }
        if (roll == 0 && pitch == 0) {
            return 0;
        }
        roll = roll * Math.PI / 180;
        pitch = pitch * Math.PI / 180;
        heading = heading * Math.PI / 180;
        double vn = -(Math.cos(roll) * Math.sin(pitch) * Math.cos(heading) + Math.sin(roll) * Math.sin(heading));
        double ve = -(Math.cos(roll) * Math.sin(pitch) * Math.sin(heading) - Math.sin(roll) * Math.cos(heading));
        double angle = Math.acos(vn / (Math.sqrt(Math.pow(vn, 2) + Math.pow(ve, 2)))) * 180 / Math.PI;
        ;
        if (ve < 0) {
            angle = 360 - angle;
        }
        return angle;
    }
}
