package kr.co.cslee.ysko.test.vo;

public class TestVo {
	private Long mbrNo;
	private String idx;
	private String name;

	public TestVo() {
	}

	public TestVo(String idx, String name) {
		this.idx = idx;
		this.name = name;
	}

	public Long getMbrNo() {
		return mbrNo;
	}

	public void setMbrNo(Long mbrNo) {
		this.mbrNo = mbrNo;
	}

	public String getIdx() {
		return idx;
	}

	public void setIdx(String idx) {
		this.idx = idx;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
