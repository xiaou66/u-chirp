import {request} from "../appService";


export interface ProductMemberInfoResp {
  /**
   * 会员 id
   */
  memberId: string;
  /**
   * 问题数
   */
  problemCount: number;
  /**
   * 点赞数
   */
  thumbsUpCount: number;
}

/**
 * 产品人员信息接口
 * @param productCode 产品 code
 */
export async function productMemberInfoApi(productCode: string) {
  return await request<ProductMemberInfoResp>('GET', '/product/member/info', {
    params: {
      productCode
    }
  });
}

export interface ProductInfoResp {
  /**
   * 产品 logo
   */
  productLogo: string;

  /**
   * 产品名称
   */
  productName: string;

  /**
   * 产品 code
   */
  productCode: string;

  /**
   * 产品状态
   */
  productStatus: number;
}

/**
 * 产品基本信息
 * @param productCode
 */
export async function productInfoApi(productCode: string) {
  return await request<ProductInfoResp>('GET', '/product/info', {
    params: {
      productCode
    }
  });
}
