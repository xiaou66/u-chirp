import dayjs, {Dayjs} from "dayjs";


// 检查是否是今天
function isToday(time: Dayjs) {
  return dayjs().isSame(time, 'day');
}

// 检查是否是昨天
function isYesterday(time: Dayjs) {
  return dayjs().subtract(1, 'day').isSame(time, 'day');
}

/**
 * 格式化为用户友好时间
 * @param createTime
 */
export function formatUserTime(createTime: string | number) {
  const now = dayjs();
  const createdTime = dayjs(createTime);

  // 计算时间间隔
  const diffInMinutes = now.diff(createdTime, 'minute');
  if (diffInMinutes < 15) {
    return '刚刚';
  } else if (diffInMinutes < 60) {
    return `${diffInMinutes}分钟前`;
  }

  if (isToday(createdTime)) {
    return `今日 ${createdTime.format('HH:mm')}`;
  } else if (isYesterday(createdTime)) {
    return `昨天 ${createdTime.format('HH:mm')}`;
  } else {
    return createdTime.format('YYYY-MM-DD HH:mm');
  }
}
